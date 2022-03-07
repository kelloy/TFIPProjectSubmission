import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { foodService } from '../food.service';
import { food } from '../model';

@Component({
  selector: 'app-foodsearch',
  templateUrl: './foodsearch.component.html',
  styleUrls: ['./foodsearch.component.css']
})
export class FoodsearchComponent implements OnInit {

  form!: FormGroup;
  foodArray: food[] = []
  username = ''

  constructor(private fb:FormBuilder, private foodSvc:foodService, private router:Router, private activatedRouter:ActivatedRoute) { }

  ngOnInit(): void {
    this.username = this.activatedRouter.snapshot.params['username']
    console.info(this.username)
    this.form = this.createForm();
  }

  createForm(): FormGroup{
    return this.fb.group({
      foodName: this.fb.control('',[Validators.required,Validators.minLength(3)])
    })
  }

  go(){
    const data = this.form.value
    console.log(data["foodName"])
    this.foodSvc.getFoodList(data["foodName"]).then(result =>{
      this.foodArray = result
      console.info(this.foodArray);
    })
  }

  goFood(uuid: string, username: string){
    console.info(username)
    this.router.navigate(['/food', username,uuid])
  }



}
