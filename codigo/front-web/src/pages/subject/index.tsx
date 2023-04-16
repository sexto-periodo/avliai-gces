import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React from 'react'

import styles from './subject.module.scss'
import {Subject} from "@mui/icons-material";
import SubjectDetails from "@/shared/components/subject-details";
import {ISubject} from "@/shared/models/ISubject";


const subject: ISubject = {
    name: 'Engenharia de software',
    score: 2.1,
    shortDescription: 'A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável.',
    imageUrl: 'https://igormiranda.com.br/wp-content/uploads/2022/10/polyphia-steve-vai.jpg',
    university: 'PUC Minas',
    longDescription: 'A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável',

}

export default function Disciplinas() {
    return (
        <GenericPageLayout title="Disciplina">
            <div className={styles.subjectMainContainer}>

                <div className={styles.leftView}>
                    <SubjectDetails subject={subject}/>
                </div>

                <div className={styles.rightView}>
                    <SubjectDetails subject={subject}/>
                </div>
            </div>
        </GenericPageLayout>
    )
}
