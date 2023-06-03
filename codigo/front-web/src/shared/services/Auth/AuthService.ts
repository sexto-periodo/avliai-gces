import {IUser, IUserCreateRequest, UserAuth} from '../../domain/User/User';
import {getCookie, hasCookie, setCookie} from "cookies-next";
import {ISignUpForm} from "@/pages/auth/signup";

export const USER_AUTH_COOKIE = 'auth';
export const USER_DATA_COOKIE = 'user_data'

export class AuthService {

    buildDefaultHeaderPlainText(): Headers;
    buildDefaultHeaderPlainText(access_token: string): Headers;
    buildDefaultHeaderPlainText(access_token?: string): Headers {
        return new Headers({
            'Accept': 'application/json',
            'Content-Type': 'text/plain',
            'Authorization': `Bearer ${access_token || this.getActualToken()}`
        });

    }

    buildDefaultHeaderApplicationJson(): Headers;
    buildDefaultHeaderApplicationJson(access_token: string): Headers;
    buildDefaultHeaderApplicationJson(access_token?: string): Headers {
        return new Headers({
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${access_token || this.getActualToken()}`
        });

    }


    buildBasicHeaderPlainText(): Headers {
        return new Headers({
            'Accept': 'application/json',
            'Content-Type': 'text/plain',
        });
    }

    buildBasicHeaderApplicationJson(): Headers {
        return new Headers({
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        });
    }

    register(request: ISignUpForm): Promise<UserAuth> {
        const body = JSON.stringify(this.ISignUpFormToIUserCreateRequest(request))
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/auth/register`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: body
        })
            .then(res => res.json())
            .then(data => {
                console.log(data)
                return data as UserAuth
            });
    }

    ISignUpFormToIUserCreateRequest(formData: ISignUpForm): IUserCreateRequest {
        return {
            firstname: formData.firstname,
            lastname: formData.lastname,
            email: formData.email,
            password: formData.password,
            academicRegister: formData.academicRegister,
            universityHashId: formData.university?.hashId,
            courseHashId: formData.course?.hashId,
            role: formData.role
        } as IUserCreateRequest;
    }

    getActualToken() {
        if (hasCookie(USER_AUTH_COOKIE)) {
            return (JSON.parse(<string>getCookie(USER_AUTH_COOKIE)) as UserAuth).access_token;
        }
    }

    /*TODO:
    - Fazer um GET dos dados do usuário e salver em cookies
     */
    async startUserSession(userAuth: UserAuth, user: IUser) {
        setCookie(USER_AUTH_COOKIE, JSON.stringify(userAuth));
        setCookie(USER_DATA_COOKIE, JSON.stringify(user));
    }

    getUserAuth() : UserAuth {
        return JSON.parse(<string>getCookie(USER_AUTH_COOKIE)) as UserAuth
    }

    haveAuthStateChanged() {
        if (hasCookie(USER_AUTH_COOKIE)) {
            return false;
        }
        return true;
    }

    endUserSession() {
        setCookie(USER_AUTH_COOKIE, null);
        setCookie(USER_DATA_COOKIE, null);
    }

    getUserData(): Promise<IUser> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user`, {
            method: 'GET',
            headers: this.buildDefaultHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                return data as IUser
            });
    }

  async validateUserSession() {
        const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Authorization': `Bearer ${this.getActualToken()}`
            },
            // Não esperar um corpo na resposta
            // Se o servidor não retornar um corpo, isso evita a tentativa de fazer parse do JSON vazio
            // e permite verificar apenas o código de status
            body: null
        });

      if (response.status === 200) {
          // Token de usuário válido
          console.log('Token válido');
          return true;
      } else {
          // Token de usuário inválido ou erro na requisição
          console.log('Token inválido ou erro na requisição');
          return false;
      }
    }
}


