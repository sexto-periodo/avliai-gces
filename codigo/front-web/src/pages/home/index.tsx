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
        imageUrl: 'https://igormiranda.com.br/wp-content/uploads/2022/10/polyphia-steve-vai.jpg',
        university: 'PUC Minas'

    },
    {
        name: 'Cálculo II',
        score: 4.6,
        shortDescription: 'Uma disciplina avançada de matemática que segue o Cálculo 1, e que geralmente é cursada por estudantes de graduação em ciências exatas, como engenharia, física e matemática.',
        imageUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROIZP-rIBerbPmWSSDwvOJhSWZ1_AnxGPqJQ&usqp=CAU',
        university: 'PUC Minas'
    },
    {
        name: 'Gestão de projetos',
        score: 4.9,
        shortDescription: 'a disciplina que se dedica a planejar, organizar, executar, monitorar e controlar projetos com o objetivo de alcançar resultados específicos dentro de um prazo, orçamento e qualidade definidos. A gestão de projetos envolve o uso de processos, técnicas, ferramentas e metodologias para garantir que os projetos sejam concluídos com sucesso, atendendo às necessidades e expectativas dos stakeholders.',
        imageUrl: 'https://i.ytimg.com/vi/MV0hyTaq_C8/maxresdefault.jpg',
        university: 'PUC Minas'
    },
    {
        name: 'Teoria dos Grafos e Computabilidade',
        score: 4.3,
        shortDescription: 'Grafos são estruturas matemáticas que representam relações entre objetos, onde esses objetos são chamados de vértices (ou nós) e as relações entre eles são chamadas de arestas (ou arcos). Os grafos são compostos por um conjunto finito de vértices e um conjunto finito de arestas, onde cada aresta conecta dois vértices.',
        imageUrl: 'https://pbs.twimg.com/profile_images/1628500998686707712/o7r8EcWD_400x400.jpg',
        university: 'PUC Minas'
    }, {
        name: 'Uma disciplina com um nome realmente muito grande e prolongoado para testes de responsividade',
        score: 5.0,
        shortDescription: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eget finibus neque. Phasellus ultrices nisi sed urna faucibus cursus. Fusce consectetur at augue ut pellentesque. Curabitur et arcu ultrices, eleifend arcu a, scelerisque nibh. Pellentesque id est in massa euismod luctus id quis erat. Nullam at enim vel lorem ultricies consequat sit amet sed turpis. Morbi vehicula dui nulla, a viverra metus consectetur et. Phasellus vehicula mauris ut erat rutrum, vel egestas est tempor. Suspendisse elementum eleifend massa a mattis.\n' +
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eget finibus neque. Phasellus ultrices nisi sed urna faucibus cursus. Fusce consectetur at augue ut pellentesque. Curabitur et arcu ultrices, eleifend arcu a, scelerisque nibh. Pellentesque id est in massa euismod luctus id quis erat. Nullam at enim vel lorem ultricies consequat sit amet sed turpis. Morbi vehicula dui nulla, a viverra metus consectetur et. Phasellus vehicula mauris ut erat rutrum, vel egestas est tempor. Suspendisse elementum eleifend massa a mattis.',
        imageUrl: 'data:image/jpeg;base64,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',
        university: 'PUC Minas'
    },
    {
        name: 'Titulo Simples',
        score: 3.6,
        shortDescription: 'Descrição pequena de disciplina',
        imageUrl: 'https://i0.wp.com/guitarload.com.br/wp-content/uploads/2022/11/electric-mob-1.jpg?fit=1200%2C628&ssl=1',
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
