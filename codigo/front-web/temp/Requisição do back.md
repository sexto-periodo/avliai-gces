

## Para a listagem de disciplinas
Uma lista de subjects: ```List<Subject>```
### Endpoint
```GET - /subjects```
#### resposta que preciso
```ts  
List<SubjectDTO> {
	id: number;  
	hashId: string;  
	name: string;  
	score:number;  
	shortDescription: string;  
	longDescription: string;  
	imageUrl: string;  
	university: String;  
}
```  
----  
## Para detalhes da disciplina

### Endpoint
```GET - /subject/{hashId}```
#### resposta que preciso
```ts  
SubjectDTO{
	id: number;  
	hashId: string;  
	name: string;  
	score:number;  
	shortDescription: string;  
	longDescription: string;  
	imageUrl: string;  
	university: String;  
	reviews: List<ReviewDTO>;  
}  
ReviewDTO{        
id: number;  
	hashId: string;  
	text: string;  
	score: number;  
	votes: number;  
	voted: boolean;  
	upvotedByUser: boolean;  
	downvotedByUser: boolean;  
	reviewerImage: string;  
}
```  
----  
## Para upvote e downvotes em comentários

### Endpoint
```PUT - /review/vote/{hashId}```

#### Requisição que farei
```ts  
VoteRequestDTO{
	id: number;  
	hashId: string;  
	voted: boolean;  
	upvotedByUser: boolean;  
	downvotedByUser: boolean;  
}
```  
#### resposta que preciso
```ts  
List<ReviewDTO>{
id: number;  
	hashId: string;  
	text: string;  
	score: number;  
	votes: number;  
	voted: boolean;  
	upvotedByUser: boolean;  
	downvotedByUser: boolean;  
	reviewerImage: string;  
}
```  
---  
## Para avaliação de disciplinas

### Endpoint
```POST - /review```

#### Requisição que farei
```ts  
ReviewRequestDTO{        
	score: number;  
	reviewText: String;  
}
```  
#### resposta que preciso
```ts  
HttpStatus.CREATED; 
List<ReviewDTO>{         
	id: number;  
	 hashId: string;  
	 text: string;  
	 score: number;  
	 votes: number;  
	 voted: boolean;  
	 upvotedByUser: boolean;  
	 downvotedByUser: boolean;  
	 reviewerImage: string;  
 }
 ```