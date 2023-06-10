import React, {useEffect, useState} from 'react';
import styles from './vote.module.scss'

import {BsFillArrowUpSquareFill, BsFillArrowDownSquareFill} from 'react-icons/bs'
import {VoteService} from "@/shared/domain/Vote/VoteService";
import {IVoteRequestDTO, IVoteDTO} from "@/shared/domain/Vote/IVote";

export default function Vote(props: IVoteDTO) {

    const voteService: VoteService = new VoteService()

    const [vote, setVote] = useState<boolean>(false);
    const [upvoteDownvote, setUpvoteDownvote] = useState<boolean>(false);
    const [voteCount, setVoteCount] = useState<number>(0);

    useEffect(() => {
        setUpvoteDownvote(props.voteUpDown)
        setVote(props.isVoted)
    }, []);

    function updateVote(newVote: boolean) {
        let keepVote = !(newVote == upvoteDownvote);


        let voteRequest: IVoteRequestDTO = {
            reviewHashId: props.reviewHashId,
            isVoted: keepVote,
            voteUpDown: newVote
        }
        voteService.vote(voteRequest)
            .then((vote) => {
                setVote(vote.isVoted)
                setVoteCount(vote.voteCount);
                setUpvoteDownvote(vote.voteUpDown)
            })
    }

    return (
        <div className={styles.voteContainer}>
            <div className={styles.upvoteBox}
                 onClick={() => updateVote(true)}
                 style={{color: `${upvoteDownvote && vote ? 'green' : 'unset'}`}}
            >
                <BsFillArrowUpSquareFill/>
            </div>
            <div className={styles.voteCountBox}>
                {voteCount}
            </div>
            <div className={styles.downvoteBox}
                 onClick={() => updateVote(false)}
                 style={{color: `${!upvoteDownvote && vote ? 'red' : 'unset'}`}}
            >
                <BsFillArrowDownSquareFill/>
            </div>
        </div>

    );
}