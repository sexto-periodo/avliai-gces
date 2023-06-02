
export interface ISubjectDTO{
    id: number;
    hashId: string;
    name: string;
    imageUrl: string;
    campus: string;
    courseHashId: string;
    universityHashId: string
    shortDescription: string,
    longDescription: string,
    score: number
}

export interface ISubject{
    name?: string
    score?:number
    shortDescription?: string
    longDescription?: string
    imageUrl?: string
    university?: String
}

