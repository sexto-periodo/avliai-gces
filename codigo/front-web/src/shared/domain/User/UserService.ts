import {getCookie, hasCookie, setCookie} from 'cookies-next';
import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";
import {IUser, IUserUpdateDataForm, IUserUpdateResponse} from "@/shared/domain/User/User";
import {AuthService} from "@/shared/services/Auth/AuthService";
import resolveUrlLoader from "next/dist/build/webpack/loaders/resolve-url-loader";

const USER_DATA_COOKIE = 'user_data';

export class UserService{

    authService: AuthService = new AuthService();
    validateEmail(email:string):Promise<boolean>{
        const body = JSON.stringify({
            email: email,
        })
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user/verify-email`, {
            method: 'POST',
            headers: this.authService.buildBasicHeaderApplicationJson(),
            body: body
        })
            .then(res => res.json())
            .then(data => {
                return data.validEmail as boolean
            });
    }

    getUserData() : IUser | null{
        if (hasCookie(USER_DATA_COOKIE)){
            return JSON.parse(getCookie(USER_DATA_COOKIE) as any) as IUser;
        }
        return null
    }

    updateUserDataCookie(user: IUser){
        setCookie(USER_DATA_COOKIE, JSON.stringify(user))
    }


    updateUserData(userFormData: IUserUpdateDataForm):Promise<IUser>{
        const body = JSON.stringify(userFormData);
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user`, {
            method: 'PUT',
            headers: this.authService.buildDefaultHeaderApplicationJson(),
            body: body
        })
            .then((res) => res.json())
            .then((data) => {
                    if ( data ){
                        this.updateUserDataCookie(data);
                    }
                    return data;
                }
            )
    }
}