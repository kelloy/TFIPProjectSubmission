import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { foodService } from '../food.service';
import { favourite, food } from '../model';
@Component({
  selector: 'app-favouritelist',
  templateUrl: './favouritelist.component.html',
  styleUrls: ['./favouritelist.component.css']
})
export class FavouritelistComponent implements OnInit {

  username = ''
  favouritelist: favourite[] = []

  constructor(private activatedRoute:ActivatedRoute, private foodSvc:foodService,private router:Router) { }

  ngOnInit(): void {
    this.username = this.activatedRoute.snapshot.params['username']
    this.foodSvc.getUserFavourites(this.username).then(
      result =>{
        this.favouritelist = result
        console.info(this.favouritelist)
      }).catch(error =>
        console.info(error))

  }

  goSearch(){
    this.router.navigate(['/search',this.username])
  }

  goFood(uuid: string, username: string){
    console.info(username)
    this.router.navigate(['/food', username,uuid])
  }

  delete(favourite:favourite){
    console.info(favourite)
    this.foodSvc.deleteFood(favourite).then(result=>{
      console.info(result)
    })
    this.router.navigate(['/search',this.username])
  }


}
