import { Injectable } from '@angular/core';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
// import { catchError } from 'rxjs/operators';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { ResponseModel } from '../_model/response';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable()
export class ExecutionService {

    private baseUrl = environment.apiUrl;  // URL to web api

    // private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) {
    }

    postExecute(idExecution: string, value: string): Observable<ResponseModel> {
        return this.http.post<ResponseModel>(
            this.baseUrl + '/execution/' + idExecution, value, httpOptions
        );

        //.pipe(catchError(this.handleError));
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        // return an ErrorObservable with a user-facing error message
        return new ErrorObservable(
            'Something bad happened; please try again later.');
    }

    // private handleError(error: any): Promise<any> {
    //     console.error('Error', error); // for demo purposes only
    //     return Promise.reject(error.message || error);
    // }
}
