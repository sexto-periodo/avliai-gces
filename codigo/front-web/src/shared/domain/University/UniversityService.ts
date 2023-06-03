import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {AuthService} from "@/shared/services/Auth/AuthService";


export class UniversityService {
    authService: AuthService = new AuthService();

    getUniversities(): Promise<IUniversityDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/university`, {
            method: 'GET',
            headers: this.authService.buildBasicHeader()
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
            headers: this.authService.buildDefaultHeader(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as IUniversityDTO;
            });
    }
}