import GenericPageLayout from '@/shared/layout/generic-page-layout/genericPageLayout'
import React from 'react'
import CardSubject from '../../shared/components/card-subject';
import Masonry from '@mui/lab/Masonry';

import styles from './home.module.scss'
import {ISubject} from "@/shared/models/ISubject";
import {FormControl, InputAdornment, InputLabel, OutlinedInput} from '@mui/material';
import {ImSearch} from 'react-icons/im'


const mockSubjects: Array<ISubject> = [
    {
        name: 'Engenharia de software',
        score: 2.1,
        shortDescription: 'A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável.',
        imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
        university: 'PUC Minas',
        longDescription: 'A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável A engenharia de software é uma disciplina que se dedica ao desenvolvimento de software de forma sistemática e disciplinada. Ela envolve o uso de princípios, métodos e técnicas para projetar, implementar, testar e manter o software de forma eficiente e confiável',

    },
    {
        name: 'Cálculo II',
        score: 4.6,
        shortDescription: 'Uma disciplina avançada de matemática que segue o Cálculo 1, e que geralmente é cursada por estudantes de graduação em ciências exatas, como engenharia, física e matemática.',
        imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
        university: 'PUC Minas'
    },
    {
        name: 'Gestão de projetos',
        score: 4.9,
        shortDescription: 'a disciplina que se dedica a planejar, organizar, executar, monitorar e controlar projetos com o objetivo de alcançar resultados específicos dentro de um prazo, orçamento e qualidade definidos. A gestão de projetos envolve o uso de processos, técnicas, ferramentas e metodologias para garantir que os projetos sejam concluídos com sucesso, atendendo às necessidades e expectativas dos stakeholders.',
        imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
        university: 'PUC Minas'
    },
    {
        name: 'Teoria dos Grafos e Computabilidade',
        score: 4.3,
        shortDescription: 'Grafos são estruturas matemáticas que representam relações entre objetos, onde esses objetos são chamados de vértices (ou nós) e as relações entre eles são chamadas de arestas (ou arcos). Os grafos são compostos por um conjunto finito de vértices e um conjunto finito de arestas, onde cada aresta conecta dois vértices.',
        imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
        university: 'PUC Minas'
    }, {
        name: 'Uma disciplina com um nome realmente muito grande e prolongoado para testes de responsividade',
        score: 5.0,
        shortDescription: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eget finibus neque. Phasellus ultrices nisi sed urna faucibus cursus. Fusce consectetur at augue ut pellentesque. Curabitur et arcu ultrices, eleifend arcu a, scelerisque nibh. Pellentesque id est in massa euismod luctus id quis erat. Nullam at enim vel lorem ultricies consequat sit amet sed turpis. Morbi vehicula dui nulla, a viverra metus consectetur et. Phasellus vehicula mauris ut erat rutrum, vel egestas est tempor. Suspendisse elementum eleifend massa a mattis.\n' +
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eget finibus neque. Phasellus ultrices nisi sed urna faucibus cursus. Fusce consectetur at augue ut pellentesque. Curabitur et arcu ultrices, eleifend arcu a, scelerisque nibh. Pellentesque id est in massa euismod luctus id quis erat. Nullam at enim vel lorem ultricies consequat sit amet sed turpis. Morbi vehicula dui nulla, a viverra metus consectetur et. Phasellus vehicula mauris ut erat rutrum, vel egestas est tempor. Suspendisse elementum eleifend massa a mattis.',
        imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
        university: 'PUC Minas'
    },
    {
        name: 'Titulo Simples',
        score: 3.6,
        shortDescription: 'Descrição pequena de disciplina',
        imageUrl: 'https://www.shutterstock.com/image-photo/young-african-american-man-using-260nw-2064750014.jpg',
        university: 'PUC Minas'
    }


]
export default function Disciplinas() {
    return (
        <GenericPageLayout title="AvaliAí">
            <div className={styles.container}>
                <div className={styles.pageFunctionsWrapper}>
                    <div className={styles.chipFilters}>
                        <h1>CHIP</h1>
                        <h1>CHIP</h1>
                        <h1>CHIP</h1>
                    </div>
                    <div className={styles.searchInputContainer}>

                        <div className={styles.inputItemWrapper}>
                            <div className={styles.inputSearchIconContainer}>
                                <ImSearch/>
                            </div>
                            <div className={styles.inputBox}>
                                <input type="text" placeholder="Pesquise por disciplinas"/>
                            </div>
                            <div className={styles.inputSearchImageContainer}>
                                <img
                                    src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.pixelstalk.net%2Fwp-content%2Fuploads%2F2016%2F04%2FKung-Fu-Panda-Wallpapers-HD.jpg&f=1&nofb=1&ipt=d74bdc8761099b4d75561600917d549a805a025f34cd90c60d040c728305fb16&ipo=images"
                                    alt=""/>
                            </div>
                        </div>

                    </div>
                </div>
                <div className={styles.subjectWrapper}>
                    <Masonry
                        columns={{xs: 1, sm: 2, md: 2, lg: 3, xl: 4}}
                        spacing={2}
                    >
                        {mockSubjects.map((item, key) => (
                            <CardSubject
                                key={key}
                                name={item.name}
                                score={item.score}
                                imageUrl={item.imageUrl}
                                shortDescription={item.shortDescription}
                                university={item.university}
                            />
                        ))}
                    </Masonry>
                </div>
            </div>
        </GenericPageLayout>
    )
}
