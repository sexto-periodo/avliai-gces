import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React, {useEffect, useState} from 'react'

import styles from './subject.module.scss'
import SubjectDetails from "@/shared/components/subject-details";
import {ISubjectDTO} from "@/shared/domain/Subject/ISubject";
import SubjectReview from "@/shared/components/subject-review";
import {ISubjectReviewDTO} from "@/shared/domain/SubjectReview/ISubjectReview";
import {SubjectService} from "@/shared/domain/Subject/SubjectService";
import {SubjectReviewService} from "@/shared/domain/SubjectReview/SubjectReviewService";
import {useRouter} from "next/router";


export default function Disciplinas(props: any) {

    const subjectService: SubjectService = new SubjectService()
    const subjectReviewService: SubjectReviewService = new SubjectReviewService()


    const [subject, setSubject] = useState<ISubjectDTO>({} as ISubjectDTO);
    const [reviews, setReviews] = useState<ISubjectReviewDTO[]>([]);

    const router = useRouter();
    const { subjectHashId } = router.query


    useEffect(() => {
        subjectService.getSubjectByHashId(subjectHashId).then((subject) => setSubject(subject))
        subjectReviewService.getReviewsBySubjectHashId(subjectHashId).then((reviews) => setReviews(reviews))
    }, [])

    return (
        <GenericPageLayout title="Disciplina">
            <div className={styles.subjectMainContainer}>

                <div className={styles.leftView}>
                    <SubjectDetails {...subject}/>
                </div>

                <div className={styles.rightView}>
                    {
                        reviews.map((item, key) =>
                            <SubjectReview key={key} review={item}/>
                        )
                    }

                </div>
            </div>
        </GenericPageLayout>
    )
}
