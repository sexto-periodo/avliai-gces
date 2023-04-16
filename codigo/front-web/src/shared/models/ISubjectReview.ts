export interface  ISubjectReview {

    text: string,
    score: number,
    votes: number,
    voted: boolean,
    upvotedByUser: boolean,
    downvotedByUser: boolean,

    reviewerImage: string
}