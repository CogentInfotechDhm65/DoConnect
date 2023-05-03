import { Component, OnInit } from '@angular/core';
import { Question } from '../model/question';
import { QuestionService } from '../service/questionService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-post',
  templateUrl: './admin-post.component.html',
  styleUrls: ['./admin-post.component.css']
})
export class AdminPostComponent implements OnInit{

  questions:Question[]=[]
  question:Question=new Question(0, "", "", "", "", "", "", 0, 0)
  
  ngOnInit(): void {
    
    /*this.questionService.getAllQuestions().subscribe((data: Question[])=>{
      this.questions=data
    });*/
    this.loadPosts();
  }

  constructor(private questionService:QuestionService, router:Router) {}

  loadPosts() {
    this.questionService.getPendingQuestions().subscribe((data: Question[])=>{
      this.questions=data
    });
  }

  approvePost(quest:Question){
    this.questionService.approveQuestion(quest).subscribe(()=>
      {
      this.loadPosts();
     });
  }

  denyPost(quest:Question){
    this.questionService.denyQuestion(quest).subscribe(()=>
    {
      this.loadPosts();
    }
    );
  }


}
