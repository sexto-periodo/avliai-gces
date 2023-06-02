import React, {useEffect, useState} from 'react'

import {ISubject} from '@/shared/domain/Subject/ISubject'
import styles from './style.module.scss'
import {AiFillStar} from 'react-icons/ai';
import Image from "next/image";
import axios from 'axios';

export default function CardSubject(props: ISubject) {
    // const [imageUrl, setImageUrl] = useState<string>('');
    //
    // useEffect(() => {
    //     async function fetchRandomImage() {
    //         try {
    //             let response: any = {};
    //             if (props.imageUrl != null) {
    //                 response = await axios.get(props.imageUrl);
    //             }
    //             const randomImageUrl = response.data.url as string; // Ajuste para o formato correto de acordo com a API que você estiver usando
    //             setImageUrl(randomImageUrl);
    //         } catch (error) {
    //             console.error('Erro ao buscar a imagem:', error);
    //         }
    //     }
    //
    //     fetchRandomImage();
    // }, []);


    return (
        <div className={styles.cardContainer}>
            <div className={styles.cardImageContainer}>
                <img src={props.imageUrl} alt=""/>
            </div>
            <div className={styles.cardContentContainer}>
                <div className={styles.primaryInfo}>
                    <div className={styles.titlesWrapper}>
                        <h3>{props.name}</h3>
                        <h4>{props.university}</h4>
                    </div>
                    <div className={styles.scoreWrapper}>
                        <div className={styles.scoreBox}>
                            <p className={styles.score}>
                                {props.score}
                            </p>
                            <p className={styles.starIcon}><AiFillStar/></p>
                        </div>
                    </div>
                </div>
                <div className={styles.secondaryInfo}>
                    <p>
                        {props.shortDescription}
                    </p>
                </div>
            </div>
        </div>
    )
}
