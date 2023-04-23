import {Rating, TextField} from '@mui/material';
import React from 'react';
import styles from './reviewModal.module.scss'
import {MdClose} from "react-icons/md";
import Button from "@mui/material/Button";

interface IModal {
    onClose: Function
}

export default function ReviewModal(props: IModal) {

    const [value, setValue] = React.useState<number | null>(2);

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
                           rows={10}/>
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
                        onClick={() => props.onClose()}
                >
                    Avaliar!
                </Button>
            </div>
        </div>
    );
};