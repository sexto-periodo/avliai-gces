import { createContext, useContext, useEffect, useState } from 'react'
import { endUserSession, getUserAuth, haveAuthStateChanged, startUserSession } from '../services/Auth/AuthService';
import { UserAuth } from '../domain/User/User';





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
  const [user, setUser] = useState<UserAuth|null>(null);
  const [loading, setLoading] = useState(true)



  /**
   * Uma das primeiras funções a serem executas. Essa em específico
   * é executada uma vez para verificar se existe uma instancia
   * da sessão do usuário no firebase. Caso isso seja verdade
   * o contexto é alterado para que todos reconheçam o usuário logado
   */
  useEffect(() => {

    let usr = getUserAuth();
    console.log("useEffect User: "+usr)
    usr ? setUser(usr as any) : setUser(null)
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
  const login = (email: string, password: string) => {


    const body = JSON.stringify({
      email: email,
      password: password,
    })

    console.log(body)

    console.log(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/auth/authenticate`);

    return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/auth/authenticate`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: body
    }).then(res => res.json()).
    then( data => {
      console.log(data)
      startUserSession({access_token: data.access_token} as UserAuth).then(() =>
          setUser({access_token: data.access_token} as UserAuth)
      )
    });

  }

  /**
   * Essa função faz logout do uauário
   */
  const logout = () => {
    endUserSession();
    setUser({} as UserAuth);
    //await signOut(auth)
  }

  /*****
   * Retornando o JSX estrutural do contexto.
   * o parâmetro children, diz respesti a literalmente todos
   * os componentes filhos.
   */
  return (
    <AuthContext.Provider value={{ user, login, signup, logout }}>
      {loading ? null : children}
    </AuthContext.Provider>
  )
}

