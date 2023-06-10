import {AuthService} from "@/shared/services/Auth/AuthService";

import {
    ICreateReviewRequestDTO,
    IReviewDTO
} from "@/shared/domain/Review/IReview";

export class ReviewService {

    authService: AuthService = new AuthService();

    getReviewsBySubjectHashId(subjectHashId: string | string[] | undefined): Promise<IReviewDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/review/subject/${subjectHashId}`, {
            method: 'GET',
            headers: this.authService.buildDefaultHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as IReviewDTO[];
            });
    }

    postReviewCreateMessage(body: ICreateReviewRequestDTO) {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/review`, {
            method: 'POST',
            headers: this.authService.buildDefaultHeaderApplicationJson(),
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            });
    }
    haveSubjectAlreadyReviewedByUser(subjectHashId: string | string[] | undefined): Promise<boolean> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/review/subject/already-reviewed-by-user/${subjectHashId}`, {
            method: 'GET',
            headers: this.authService.buildDefaultHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as boolean;
            });
    }

    getReviewsByUser(): Promise<IReviewDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/review/user`, {
            method: 'GET',
            headers: this.authService.buildDefaultHeaderApplicationJson(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as IReviewDTO[];
            });
    }
}