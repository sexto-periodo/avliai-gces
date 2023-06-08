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
import {ModalType} from "@/shared/components/modal/ModalEnum";
import ReviewModal from "@/shared/components/modal/review-modal";
import {createPortal} from "react-dom";
import Modal from "@/shared/components/modal/ModalLayout";
import Button from "@mui/material/Button";
import {toast, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import Confetti from 'react-confetti'
import useWindowSize from 'react-use/lib/useWindowSize'

interface IModalControl {
    show: boolean
    modalType: ModalType
}
export async function getServerSideProps(context: any) {
    return {
        props: {},
    };
}

export default function Disciplina() {


    const subjectService: SubjectService = new SubjectService()
    const subjectReviewService: SubjectReviewService = new SubjectReviewService()


    const [subject, setSubject] = useState<ISubjectDTO>({} as ISubjectDTO);
    const [reviews, setReviews] = useState<ISubjectReviewDTO[]>([]);
    const [isSubjectAlreadyReviewedByUser, setIsSubjectAlreadyReviewedByUser] = useState<boolean>(false);

    const router = useRouter();
    const subjectHashId = router.query.slug;

    useEffect(() => {
        subjectService.getSubjectByHashId(subjectHashId).then((subject) => setSubject(subject));
        subjectReviewService.getReviewsBySubjectHashId(subjectHashId).then((reviews) => setReviews(reviews))
        subjectReviewService.haveSubjectAlreadyReviewedByUser(subjectHashId).then((reviewed) => setIsSubjectAlreadyReviewedByUser(reviewed));

    }, [])


    const [showModal, setShowModal] = useState<IModalControl>({
        show: false,
        modalType: ModalType.REVIEW
    });

    function openModal(modalType: ModalType) {
        setShowModal({show: true, modalType: modalType});
    }

    function closeModal() {
        console.log("Fechando Modal")
        successReviewRequest()
        subjectReviewService.getReviewsBySubjectHashId(subjectHashId)
            .then((reviews) => setReviews(reviews)).then(
            () => setShowModal({show: false, modalType: ModalType.REVIEW})
        ).then(() => setIsSubjectAlreadyReviewedByUser(true));

    }

    function successReviewRequest() {
        toast.success('Avaliação enviada com sucesso!', {
            position: "top-right",
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        });
    }

    function reviewerBlockedAlert(){
        toast.info('Você ja avaliou essa disciplina.', {
            position: "top-right",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        });
    }

    function buildModal() {

        switch (showModal.modalType) {
            case ModalType.REVIEW:
                return <ReviewModal
                    onClose={closeModal}
                    subjectHashId={subject.hashId}
                    courseHashId={subject.courseHashId}
                    universityHashId={subject.universityHashId}
                />
            default:
                return null;
        }
    }


    return (
        <>
            <GenericPageLayout title="Disciplina">
                <div className={styles.subjectMainContainer}>

                    <div className={styles.leftView}>
                        <SubjectDetails {...subject}/>
                    </div>

                    <div className={styles.rightView}>
                        <div className={styles.reviewButtonContainer}>

                            {
                                isSubjectAlreadyReviewedByUser ?
                                    <Button variant="contained"
                                            disableElevation
                                            type="submit"
                                            sx={{ml: 1, mr: 1, mt: 0}}
                                            style={{
                                                borderRadius: 30,
                                                width: 200,
                                                height: 50
                                            }}
                                            disabled={true}
                                            onClick={() => reviewerBlockedAlert()}
                                    >
                                        Avaliar!
                                    </Button>
                                    :
                                    <Button variant="contained"
                                            disableElevation
                                            type="submit"
                                            sx={{ml: 1, mr: 1, mt: 0}}
                                            style={{
                                                borderRadius: 30,
                                                width: 200,
                                                height: 50
                                            }}
                                            onClick={() => openModal(ModalType.REVIEW)}
                                    >
                                        Avaliar!
                                    </Button>
                            }

                        </div>
                        <div>
                            {
                                reviews.map((item, key) =>
                                    <SubjectReview review={item} key={key}/>
                                )
                            }
                        </div>


                    </div>
                </div>
            </GenericPageLayout>
            {showModal.show && createPortal(
                <Modal>
                    {
                        buildModal()
                    }
                </Modal>,
                document.body
            )}
            <ToastContainer
                position="top-right"
                autoClose={3000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="colored"
            />

            <ToastContainer
                position="top-right"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="colored"
            />
        </>

    )
}
