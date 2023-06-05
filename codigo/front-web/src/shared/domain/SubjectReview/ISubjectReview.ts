export interface ISubjectReviewDTO {
    id: number,
    hashId: string,
    voteCount: number,
    reviewText: string,
}

export interface ISubjectReviewByUserDTO extends ISubjectReviewDTO {
    firstname: string
    lastname: string
}

export interface ICreateSubjectReviewRequestDTO {
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