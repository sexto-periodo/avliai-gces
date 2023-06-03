import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";
import {AuthService} from "@/shared/services/Auth/AuthService";


export class CourseService{

    authService: AuthService = new AuthService()
    getCoursesByUniversityHashId(universityHashId: string){
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/course/university/${universityHashId}`, {
            method: 'GET',
            headers: this.authService.buildBasicHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as ICourseDTO[];
            });
    }
}