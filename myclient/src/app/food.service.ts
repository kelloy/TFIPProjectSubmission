import { HttpClient, HttpHeaders} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { favourite, feedback, food,user, userlogin} from "./model";

@Injectable()
export class foodService{

  constructor(private http: HttpClient){}

  getFoodList(foodName:string):Promise<food[]>{
    return lastValueFrom(this.http.get<food[]>(`/api/food/${foodName}`))
  }

  getFood(uuid:string):Promise<food>{
    return lastValueFrom(this.http.get<food>(`/api/food/fooddetails/${uuid}`))
  }

  sendFeedback(feedback:feedback):Promise<string>{
    const headers = new HttpHeaders().set("Content-Type","application/json")
    return lastValueFrom(this.http.post<string>(`/api/feedback`,JSON.stringify(feedback),{headers:headers}))
  }

  getUserCredential(userlogin: userlogin):Promise<string>{
    const headers = new HttpHeaders().set("Content-Type","application/json")
    return lastValueFrom(this.http.post<string>(`/api/login`,JSON.stringify(userlogin),{headers}))

  }

  saveUserCredential(user: user){
    const headers = new HttpHeaders().set("Content-Type","application/json")
      return lastValueFrom(this.http.post<string>(`/api/register`,JSON.stringify(user),{headers}))
  }

  getUserFavourites(username:string): Promise<favourite[]>{
    return lastValueFrom(this.http.get<favourite[]>(`/api/favourite/${username}`))
  }

  saveFood(favourite:favourite): Promise<string>{
    const headers = new HttpHeaders().set("Content-Type","application/json")
    return lastValueFrom(this.http.post<string>(`/api/food/fooddetails/`,JSON.stringify(favourite),{headers}))

  }

  deleteFood(favourite:favourite): Promise<string>{
    const headers = new HttpHeaders().set("Content-Type","application/json")
    return lastValueFrom(this.http.post<string>(`/api/food/fooddetails/delete`,JSON.stringify(favourite),{headers}))
  }

}
