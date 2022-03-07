import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { foodService } from '../food.service';
import { userlogin } from '../model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form!:FormGroup;
  password= "";
  username = ""


  constructor(private fb:FormBuilder,private foodSvc:foodService,private router:Router) { }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  createForm():FormGroup{
    return this.fb.group({
      username: this.fb.control('',[Validators.required,Validators.minLength(3)]),
      password: this.fb.control('',[Validators.required,Validators.minLength(8)])})
    }

  login(){
    const data = this.form.value as userlogin
    this.foodSvc.getUserCredential(data)
    .then(result =>
      {
        this.router.navigate(['/favourite',data.username])
      }).catch(error=>{
        alert("no such username or invalid password")
      })
    }
  }
