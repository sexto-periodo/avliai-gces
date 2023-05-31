import {ISubjectDTO} from "@/shared/domain/Subject/ISubject";
import {AuthService} from "@/shared/services/Auth/AuthService";


export class SubjectService{

    authService: AuthService = new AuthService()
    getSubjects(): Promise<ISubjectDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/subject`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : `Bearer ${this.authService.getActualToken()}`
            },
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as ISubjectDTO[];
            });
    }
}
