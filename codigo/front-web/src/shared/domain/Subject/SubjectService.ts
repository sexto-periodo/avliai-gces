import {ISubjectDTO} from "@/shared/domain/Subject/ISubject";
import {AuthService} from "@/shared/services/Auth/AuthService";


export class SubjectService{

    authService: AuthService = new AuthService()
    getSubjectsByCourse(courseHashId: string): Promise<ISubjectDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/subject/course/${courseHashId}`, {
            method: 'GET',
            headers: this.authService.buildDefaultHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as ISubjectDTO[];
            });
    }

    getSubjectByHashId(hashId: string | string[] | undefined):  Promise<ISubjectDTO> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/subject/${hashId}`, {
            method: 'GET',
            headers: this.authService.buildDefaultHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as ISubjectDTO;
            });
    }
}
