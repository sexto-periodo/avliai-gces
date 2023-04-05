import React from 'react'

import { ISubject } from '@/shared/models/ISubject'
import styles from './style.module.scss'

export default function CardSubject(props: ISubject) {
  return (
    <div>
      <h3>{ props.name }</h3>
      <p>-----</p>
      <h4>{ props.score }</h4>
    </div>
  )
}
