import { UserAuth } from "../../../models/User";


export function getEmail(){
    return JSON.parse(localStorage.getItem('auth')).email as string
}

export function getActualToken(){
    return JSON.parse(localStorage.getItem('auth')).token as string
}
export async function startUserSession(user: UserAuth){
    localStorage.setItem('auth', JSON.stringify(user));
}
export function getUserAuth(){
    return JSON.parse(localStorage.getItem('auth')) as UserAuth
}
export function haveAuthStateChanged(){
    let auth = JSON.parse(localStorage.getItem('auth'));
    if( auth ){
        return false;
    }
    return true;
}
export function endUserSession(){
    localStorage.setItem('auth', null);
}