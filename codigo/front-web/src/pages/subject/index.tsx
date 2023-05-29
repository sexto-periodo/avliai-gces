import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React from 'react'

import styles from './subject.module.scss'
import {Subject} from "@mui/icons-material";
import SubjectDetails from "@/shared/components/subject-details";
import {ISubject} from "@/shared/domain/Subject/ISubject";
import SubjectReview from "@/shared/components/subject-review";
import {ISubjectReview} from "@/shared/domain/Subject/ISubjectReview";
import {number} from "prop-types";


const subject: ISubject = {
    name: 'Engenharia de software',
    score: 2.1,
    shortDescription: 'A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável.',
    imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
    university: 'PUC Minas',
    longDescription: 'A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável',

}

const reviews: ISubjectReview = {
    text: 'Lorem ipsume',
    score: 4,
    votes: 30,
    voted: false,
    upvotedByUser: false,
    downvotedByUser: false,
    reviewerImage: 'https://static.wixstatic.com/media/1ab2b6_7b9e460b47e54b34aaeec1607dd9b607~mv2.jpg/v1/fill/w_640,h_640,al_c,q_85/1ab2b6_7b9e460b47e54b34aaeec1607dd9b607~mv2.jpg'
}

export default function Disciplinas() {
    return (
        <GenericPageLayout title="Disciplina">
            <div className={styles.subjectMainContainer}>

                <div className={styles.leftView}>
                    <SubjectDetails subject={subject}/>
                </div>

                <div className={styles.rightView}>
                    <SubjectReview review={reviews}/>
                </div>
            </div>
        </GenericPageLayout>
    )
}
