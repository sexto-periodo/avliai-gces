import React, {useEffect, useState} from 'react';
import styles from './vote.module.scss'

import {BsFillArrowUpSquareFill, BsFillArrowDownSquareFill} from 'react-icons/bs'
import {VoteService} from "@/shared/domain/Vote/VoteService";
import {IVoteRequestDTO, IVoteDTO} from "@/shared/domain/Vote/IVote";

export default function Vote(props: IVoteDTO) {

    const voteService: VoteService = new VoteService()

    const [vote, setVote] = useState<boolean>(props.isVoted);
    const [upvoteDownvote, setUpvoteDownvote] = useState<boolean>(props.voteUpDown);
    const [voteCount, setVoteCount] = useState<number>(props.voteCount);

    useEffect(() => {
        setUpvoteDownvote(s => props.voteUpDown)
        setVote(s => props.isVoted)
        setVoteCount(s => props.voteCount)
    }, []);

    function updateVote(newVote: boolean) {
        let keepVote = true
        if(vote && newVote == upvoteDownvote){
            keepVote = false;
        }

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