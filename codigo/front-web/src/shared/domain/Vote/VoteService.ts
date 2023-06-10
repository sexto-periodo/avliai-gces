import {AuthService} from "@/shared/services/Auth/AuthService";
import {IVoteRequestDTO, IVoteDTO} from "@/shared/domain/Vote/IVote";

export class VoteService {

    authService: AuthService = new AuthService();

    vote(vote: IVoteRequestDTO): Promise<IVoteDTO> {
        return fetch(`${process.env.NEXT_PUBLIC_BACKEND_BASE_URL}/vote`, {
            method: 'PUT',
            headers: this.authService.buildDefaultHeaderApplicationJson(),
            body: JSON.stringify(vote)
        })
            .then(res => res.json())
            .then(data => {
                    return data;
                }
            );
    }
}