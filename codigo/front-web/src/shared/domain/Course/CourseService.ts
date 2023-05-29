import {getActualToken} from "@/shared/services/Auth/AuthService";
import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";


export class CourseService{
    getCoursesByUniversityHashId(universityHashId: string){
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/course/university/${universityHashId}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as ICourseDTO[];
            });
    }
}