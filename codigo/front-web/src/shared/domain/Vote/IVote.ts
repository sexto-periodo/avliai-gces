
export interface IVoteRequestDTO {
    isVoted: boolean
    voteUpDown: boolean
    reviewHashId: String
}

export interface IVoteDTO  {
    isVoted: boolean
    voteUpDown: boolean
    voteCount: number
    reviewHashId: string,
}