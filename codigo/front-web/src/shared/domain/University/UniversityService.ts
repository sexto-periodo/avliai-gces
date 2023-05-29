import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {getActualToken, startUserSession} from "@/shared/services/Auth/AuthService";
import {UserAuth} from "@/shared/domain/User/User";


export class UniversityService{

    getUniversities(): Promise<IUniversityDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/university`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as IUniversityDTO[];
            });
    }
}