import {AuthService} from "@/shared/services/Auth/AuthService";

import {ISubjectReviewDTO} from "@/shared/domain/SubjectReview/ISubjectReview";

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
}