import {IUserCreateRequest, UserAuth} from '../../domain/User/User';
import {getCookie, hasCookie, setCookie} from "cookies-next";
import {ISignUpForm} from "@/pages/auth/signup";
import {AuthContextProvider, useAuth} from "@/shared/contexts/Auth";



export class AuthService{

    register(request: ISignUpForm){
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

    ISignUpFormToIUserCreateRequest(formData: ISignUpForm): IUserCreateRequest{
        return {
            firstname: formData.firstname,
            lastname: formData.lastname,
            email: formData.email,
            password:formData.password,
            academicRegister: formData.academicRegister,
            universityHashId: formData.university?.hashId,
            courseHashId: formData.course?.hashId,
            role: formData.role
        } as IUserCreateRequest;
    }
}

export function getActualToken(){
    if ( hasCookie('auth')){
        return getCookie('auth');
    }
}
export async function startUserSession(user: UserAuth){
    setCookie('auth', JSON.stringify(user));
}
export function getUserAuth(){
    return getCookie('auth');
}
export function haveAuthStateChanged(){
    if( hasCookie('auth')){
        return false;
    }
    return true;
}
export function endUserSession(){
    setCookie('auth', null);
}