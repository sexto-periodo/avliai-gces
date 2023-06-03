import {createContext, useContext, useEffect, useState} from 'react'
import {
    AuthService
} from '../services/Auth/AuthService';
import {IUser, UserAuth} from '../domain/User/User';
import {UserService} from "@/shared/domain/User/UserService";

/**
 * Esse conjunto de funções é responsável pelo gerenciamento
 * do contexto de autenticação da aplicação. Com o contexto,
 * conseguimos saber se um usuário está logado e tomar decisões
 * a partir disso
 */

// Criação do contexto
const AuthContext = createContext<any>({})


// exportan funcão que usa o contexto
export const useAuth = () => useContext(AuthContext)

/**
 * Provedor de contexto que ecapsula toda a apliacação, todas
 * as rotas e acessos.
 * @param param0
 * @returns
 */
export const AuthContextProvider = ({
                                        children,
                                    }: {
    children: React.ReactNode
}) => {

    const authService: AuthService = new AuthService();
    const userService: UserService = new UserService();
    const [userAuth, setUserAuth] = useState<UserAuth | null>();
    const [loading, setLoading] = useState(true)


    /**
     * Uma das primeiras funções a serem executas. Essa em específico
     * é executada uma vez para verificar se existe uma instancia
     * da sessão do usuário no firebase. Caso isso seja verdade
     * o contexto é alterado para que todos reconheçam o usuário logado
     */
    useEffect(() => {
        let usr = authService.getUserAuth();
        setUserAuth(usr);
        console.log("useEffect User: " + usr)
        if (usr) {
            authService.validateUserSession().then((sessionStatus) => {
                console.log("A SESSÃO:" +sessionStatus)
                if(sessionStatus){
                    setUserAuth(usr)
                    debugger
                } else{
                    console.log("Teerminando e setando null")
                    authService.endUserSession();
                    setUserAuth(null)
                }
            })
        }else {
            console.log("Segundo Else")
            authService.endUserSession();
            setUserAuth(null)
        }
        setLoading(false)
    }, [])

    /**
     * Essa função é responsável por criar novas contas de usuário
     * @param email email do novo usuário
     * @param password senha do novo usuár
     * @returns uma promise da API para cadastro de usuário
     * TODO: Implementar criação de usuário
     */
    const signup = (email: string, password: string) => {
        return null
    }

    /**
     * Essa função faz login do usuário
     * @param email email de usuário
     * @param password Senha do usuário
     * @returns Função do firebase que faz a autenticação do usuário
     */
    const login = async (email: string, password: string) => {


        const body = JSON.stringify({
            email: email,
            password: password,
        })

        console.log(body)

        console.log(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/auth/authenticate`);

        let userAuthResult: UserAuth = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/auth/authenticate`, {
            method: 'POST',
            headers: authService.buildBasicHeaderApplicationJson(),
            body: body
        }).then(res => res.json()).then(data => {
            return data as UserAuth
        });

        let userResult: IUser = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user`, {
            method: 'GET',
            headers: authService.buildDefaultHeaderApplicationJson(userAuthResult.access_token),
        }).then(res => res.json()).then(data => {
            return data as IUser
        });

        authService.startUserSession(
            userAuthResult,
            userResult
        ).then(() =>
            setUserAuth(userAuthResult)
        )

    }

    /**
     * Essa função faz logout do uauário
     */
    const logout = () => {
        authService.endUserSession();
        setUserAuth({} as UserAuth);
        //await signOut(auth)
    }

    const startUserSession = async (userAuth: UserAuth) => {
        let userResult: IUser = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user`, {
            method: 'GET',
            headers: authService.buildDefaultHeaderApplicationJson(userAuth.access_token),
        }).then(res => res.json()).then(data => {
            return data as IUser
        });

        authService.startUserSession(
            userAuth,
            userResult
        ).then(() =>
            setUserAuth(userAuth)
        )
    }

    /*****
     * Retornando o JSX estrutural do contexto.
     * o parâmetro children, diz respesti a literalmente todos
     * os componentes filhos.
     */
    return (
        <AuthContext.Provider value={{userAuth, login, signup, logout, startUserSession}}>
            {loading ? null : children}
        </AuthContext.Provider>
    )
}

