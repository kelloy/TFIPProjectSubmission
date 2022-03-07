import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { foodService } from '../food.service';
import { feedback } from '../model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  form!: FormGroup;
  feedback!:feedback

  constructor(private fb:FormBuilder, private foodSvc:foodService, private router:Router) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }


  createForm():FormGroup{
    return this.fb.group({
      username: this.fb.control('',[Validators.required,Validators.minLength(3)]),
      email: this.fb.control('',[Validators.required,Validators.email]),
      contactNumber: this.fb.control('',[Validators.required,Validators.maxLength(8),Validators.minLength(8)]),
      comment: this.fb.control('',[Validators.required,Validators.minLength(20)])
    })
  }

  processForm(){
    const data = this.form.value as feedback
    this.foodSvc.sendFeedback(data).then(result=>{
      console.log(result)
      alert("Thank you for your feedback")
      this.router.navigate(['/'])
    }).catch(e => {
      console.log(e);
  });
    console.info(data);
}
}
