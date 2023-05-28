import {IUniversityDTO} from "@/shared/models/IUniversity";
import {getActualToken, startUserSession} from "@/shared/services/AuthService";
import {UserAuth} from "@/shared/models/User";


export class UniversityService{

    getUniversities(): Promise<IUniversityDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/university`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getActualToken()}`
            },
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as IUniversityDTO[];
            });
    }
}