import {IUser, IUserCreateRequest, UserAuth} from '../../domain/User/User';
import {getCookie, hasCookie, setCookie} from "cookies-next";
import {ISignUpForm} from "@/pages/auth/signup";
import {AuthContextProvider, useAuth} from "@/shared/contexts/Auth";

export const USER_AUTH_COOKIE = 'auth';
export const USER_DATA_COOKIE = 'user_data'

export class AuthService {

    register(request: ISignUpForm) {
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

    getUserAuth() {
        return getCookie(USER_AUTH_COOKIE);
    }

    haveAuthStateChanged() {
        if (hasCookie(USER_AUTH_COOKIE)) {
            return false;
        }
        return true;
    }

    endUserSession() {
        setCookie(USER_AUTH_COOKIE, null);
    }

    getUserData(): Promise<IUser> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${this.getActualToken()}`
            },
        })
            .then(res => res.json())
            .then(data => {
                return data as IUser
            });
    }

    validateUserSession(){
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/auth/validate-session`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${this.getActualToken()}`
            },
        })
            .then(r =>  r.json().then(data => ({status: r.status, body: data})))
            .then(obj => obj.status == 200)
            .catch((e) => false);

    }
}


