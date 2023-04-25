import React from 'react';
import styles from './vote.module.scss'

import {BsFillArrowUpSquareFill, BsFillArrowDownSquareFill} from 'react-icons/bs'

interface IVote {
    downvote: boolean,
    upvote: boolean,
    votes: number
}

export default function Vote(props: IVote) {
    return (
        <div className={styles.voteContainer}>
            <div className={styles.upvoteBox}>
                <BsFillArrowUpSquareFill/>
            </div>
            <div className={styles.voteCountBox}>
                { props.votes }
            </div>
            <div className={styles.downvoteBox}>
                <BsFillArrowDownSquareFill/>
            </div>
        </div>

    );
}