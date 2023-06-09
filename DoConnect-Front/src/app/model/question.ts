import { NumberSymbol } from '@angular/common';
import {User} from './user'
import { Answer } from './answer';

export class Question {
    constructor(
        public id: number,
        public description_question: String,
        public image_src: String,
        public datetime: String,
        public status: String,
        public topic: String,
        public title: String,
        public qcreated_by: User,
        public qapproved_by: User,
        
        public answers: Answer[]

    ){}
}