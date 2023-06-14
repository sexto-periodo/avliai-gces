import {IUniversityDTO} from "@/shared/domain/University/IUniversity";
import {ICourseDTO} from "@/shared/domain/Course/ICourseDTO";

export interface IUser {
    id: number;
    firstname: string;
    lastname: string;
    hashId: string;
    email: string;
    profilePhotoUrl: string
    universityHashId: string
    courseHashId: string
    role: string
}

export interface UserAuth {
    access_token: string,
    refresh_token: string,

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

export interface IUserUpdateDataForm{
    firstname: string;
    lastname: string;
    profilePhotoUrl: string
}

export interface IUserUpdateResponse{
    user: IUser,
    success: boolean
}