
export interface ISubjectDTO{
    id: number;
    hashId: string;
    name: string;
    picUrl: string;
    campus: string;
    courseHashId: string;
}

export interface ISubject{
    name?: string
    score?:number
    shortDescription?: string
    longDescription?: string
    imageUrl?: string
    university?: String
}

