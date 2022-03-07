import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { foodService } from '../food.service';
import { user } from '../model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  form!:FormGroup

  constructor(private fb:FormBuilder, private foodSvc:foodService, private router:Router) { }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  createForm():FormGroup{
    return this.fb.group({
      username: this.fb.control('',[Validators.required,Validators.minLength(3)]),
      password: this.fb.control('',[Validators.required,Validators.minLength(3)]),
      email: this.fb.control('',[Validators.required,Validators.email]),
      contactNumber: this.fb.control('',[Validators.required,Validators.minLength(8)])

    })
  }
  addUser(){
    const data = this.form.value as user
    console.info(data);
    this.foodSvc.saveUserCredential(data)
    .then(result=>{
      alert("user registered! Thank you")
    }).catch(error=>{
      alert("email already exists, please login or use another name")
    })
  }

}
