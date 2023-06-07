import {AuthService} from "@/shared/services/Auth/AuthService";

import {
    ICreateSubjectReviewRequestDTO,
    ISubjectReviewDTO
} from "@/shared/domain/SubjectReview/ISubjectReview";

export class SubjectReviewService {

    authService: AuthService = new AuthService();

    getReviewsBySubjectHashId(subjectHashId: string | string[] | undefined): Promise<ISubjectReviewDTO[]> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/subject-review/subject/${subjectHashId}`, {
            method: 'GET',
            headers: this.authService.buildDefaultHeaderPlainText(),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                return data as ISubjectReviewDTO[];
            });
    }

    postReviewCreateMessage(body: ICreateSubjectReviewRequestDTO) {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/subject-review`, {
            method: 'POST',
            headers: this.authService.buildDefaultHeaderApplicationJson(),
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            });
    }
}