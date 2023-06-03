import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {AuthService} from "@/shared/services/Auth/AuthService";


export class UniversityService {
    authService: AuthService = new AuthService();

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


    getUniversityByHashId(hashId:string): Promise<IUniversityDTO> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/university/${hashId}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' :`Bearer ${this.authService.getActualToken()}`
            },
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as IUniversityDTO;
            });
    }
}