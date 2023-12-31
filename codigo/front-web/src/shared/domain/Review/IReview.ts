import {IVoteDTO} from "@/shared/domain/Vote/IVote";

export interface IReviewDTO {
    id: number,
    hashId: string,
    reviewText: string,
    vote: IVoteDTO

}

export interface IReviewByUserDTO extends IReviewDTO {
    firstname: string
    lastname: string
}

export interface ICreateReviewRequestDTO {
    reviewText: string,
    score: string,
    userHashId: string,
    subjectHashId: string,
    universityHashId: string,
    courseHashId: string,
}

export enum EScore {
    ONE = <number>1,
    TWO = <number>2,
    THREE = <number>3,
    FOUR = <number>4,
    FIVE = <number>5,
}