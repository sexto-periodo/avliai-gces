import React from 'react'
import CardSubject from './components/card-subject'


const mockSubjects = [
  {
    name: 'Engenharia de software',
    score: 5
  },
  {
    name: 'Trabalho interdisciplinar',
    score: 4
  }
]

export default function Disciplinas() {
  return (
    <div>
        <h1>Disciplinas</h1>
        <div>
            {
              mockSubjects.map((item, key) => 
              <CardSubject 
              key={key}
              name={item.name} 
              score={item.score}
              /> )
            }
        </div>
    </div>
  )
}
