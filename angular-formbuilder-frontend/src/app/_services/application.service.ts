import { Injectable } from '@angular/core';
import 'rxjs/add/operator/toPromise';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
// import { catchError } from 'rxjs/operators';
import { FormHeader } from '../model/form-header';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { FormWrapper } from '../_wrapper/form.wrapper';
import { Application } from '../_model/application';
import { environment } from '../../environments/environment';


@Injectable()
export class ApplicationService {

    private baseUrl = environment.apiUrl;  // URL to web api
    private idApp = '';
    private idDataa = '';

    constructor(private http: HttpClient) { }

    set idApplication(value: string) {
        this.idApp = value;
    }

    get idApplication(): string {
        return this.idApp;
    }

    set idData(value: string) {
        this.idDataa = value;
    }

    get idData(): string {
        return this.idDataa;
    }

    get isStateNew(): boolean {
        if (this.idDataa == null) {
            return true;
        }
        return false;
    }

    getAppById(idApp: string): Observable<Application[]> {
        return this.http.get<Application[]>(this.baseUrl + '/app/' + idApp);

        //            .pipe(catchError(this.handleError));
    }

    getAppIdByDataId(idApp: string, idData: string): Observable<any> {
        return this.http.get(this.baseUrl + '/app/' + idApp + '/' + idData);

        //.pipe(catchError(this.handleError));
    }

    getFormList(): Observable<FormHeader[]> {
        return this.http.get<FormHeader[]>(this.baseUrl + '/form/');

        //.pipe(catchError(this.handleError));

        // return this.http.get(this.baseUrl + '/form/')
        //     .toPromise()
        //     .then(response => response.json() as FormHeader[])
        //     .catch(this.handleError);
    }

    getFormById(idForm: string): Observable<FormWrapper> {
        return this.http.get<FormWrapper>(this.baseUrl + '/form/' + idForm);


        //.pipe(catchError(this.handleError));
        // return this.http.get(this.baseUrl + '/form/' + idForm)
        //     .toPromise()
        //     .then(response => response.json() as FormWrapper)
        //     .catch(this.handleError);
    }

    getFormByIdDataId(idform: string, iddata: string): Observable<FormWrapper> {
        return this.http.get<FormWrapper>(this.baseUrl + '/form/' + idform + '/' + iddata);

        //.pipe(catchError(this.handleError));
        // return this.http.get(this.baseUrl + '/form/' + idform + '/' + iddata)
        //     .toPromise()
        //     .then(response => response.json() as FormWrapper)
        //     .catch(this.handleError);
    }

    // private handleError(error: any): Promise<any> {
    //     console.error('Error', error); // for demo purposes only
    //     return Promise.reject(error.message || error);
    // }

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
}
