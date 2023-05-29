import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";

export interface IUser {
    nome: string,
    email: string,
    acesso: string,
    senha: string
}

export interface UserAuth {
    access_token: string,
}

export interface IUserCreateRequest{
    firstname: string
    lastname: string
    email: string
    password:string
    academicRegister: string
    universityHashId: string
    courseHashId: string
    role:string
}