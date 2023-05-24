import { UserAuth } from '../models/User';
import {getCookie, hasCookie, setCookie} from "cookies-next";



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