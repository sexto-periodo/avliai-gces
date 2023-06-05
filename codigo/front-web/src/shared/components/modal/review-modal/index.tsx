import {Rating, TextField} from '@mui/material';
import React, {useEffect} from 'react';
import styles from './reviewModal.module.scss'
import {MdClose} from "react-icons/md";
import Button from "@mui/material/Button";
import {IModal} from "@/shared/components/modal/IModal";
import {UserService} from "@/shared/domain/User/UserService";
import {UniversityService} from "@/shared/domain/University/UniversityService";
import {CourseService} from "@/shared/domain/Course/CourseService";
import {
    EScore,
    ICreateSubjectReviewRequestDTO
} from "@/shared/domain/SubjectReview/ISubjectReview";
import {SubjectReviewService} from "@/shared/domain/SubjectReview/SubjectReviewService";


interface IReviewModal extends IModal{
    subjectHashId: string;
    courseHashId: string;
    universityHashId: string;
}

export default function ReviewModal(props: IReviewModal) {

    const userService: UserService = new UserService();
    const subjectReviewService: SubjectReviewService = new SubjectReviewService();

    const [value, setValue] = React.useState<number | null>(2);
    const [text, setText] = React.useState<string>("");

    useEffect(() => {
        console.log(value);
    }, [value, text])

    function buildSubjectReviewDTO(){
        // @ts-ignore
        let scoreKey = Object.keys(EScore).find(x => EScore[x] == value);
        let SubjectReviewRequest: ICreateSubjectReviewRequestDTO = {
            reviewText: text,
            universityHashId: props.universityHashId,
            courseHashId: props.courseHashId,
            subjectHashId: props.subjectHashId,
            userHashId: userService.getUserData()?.hashId || "",
            score: scoreKey || "FIVE",
        }

        subjectReviewService.postReviewCreateMessage(SubjectReviewRequest)
            .then(() => props.onClose());
    }


    return (
        <div className={styles.reviewModalContainer}>

            <div className={styles.modalHeader}>
                <div>
                    <h2>Avaliar disciplina</h2>
                </div>
                <div className={styles.closeIconContainer} onClick={() => props.onClose()}>
                    <MdClose/>
                </div>
            </div>
            <div className={styles.starsContainer}>
                <Rating
                    name="simple-controlled"
                    value={value}
                    size="large"
                    style={{fontSize: 50, color: "#4f378b"}}
                    onChange={(event, newValue) => {
                        setValue(newValue);
                    }}
                />
            </div>
            <div className={styles.reviewTextContainer}>
                <TextField fullWidth label="Justifique sua avaliação" id="fullWidth" multiline
                           rows={10}
                            onChange={(e) => setText(e.target.value)}
                />
            </div>
            <div className={styles.reviewButtonsContainer}>
                <Button variant="outlined"
                        disableElevation
                        type="submit"
                        sx={{ml:1, mr: 1, mt: 3}}
                        style={{borderRadius: 30, width: 200, height: 50}}
                        onClick={() => props.onClose()}
                >
                    Cancelar
                </Button>
                <Button variant="contained"
                        disableElevation
                        type="submit"
                        sx={{ml:1, mr: 1, mt: 3}}
                        style={{borderRadius: 30, width: 200, height: 50}}
                        onClick={() => buildSubjectReviewDTO()}
                >
                    Avaliar!
                </Button>
            </div>
        </div>
    );
};