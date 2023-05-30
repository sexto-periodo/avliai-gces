import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";
import {IUser} from "@/shared/domain/User/User";
import {AuthService} from "@/shared/services/Auth/AuthService";

export class UserService{

    authService: AuthService = new AuthService();
    validateEmail(email:string):Promise<boolean>{
        const body = JSON.stringify({
            email: email,
        })
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/user/verify-email`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: body
        })
            .then(res => res.json())
            .then(data => {
                return data.validEmail as boolean
            });
    }


}