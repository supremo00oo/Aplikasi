<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ApiController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('login', [ApiController::class, 'login']);
//pupuk
Route::get('pupuk/tampil', [ApiController::class, 'getpupuk']);
Route::post('pupuk/simpan', [ApiController::class, 'entrypupuk']);
Route::post('pupuk/hapus',[ApiController::class, 'hapuspupuk']);
Route::post('pupuk/edit',[ApiController::class, 'editpupuk']);
//benih
Route::get('benih/tampil', [ApiController::class, 'getbenih']);
Route::post('benih/simpan', [ApiController::class, 'entrybenih']);
Route::post('benih/hapus',[ApiController::class, 'hapusbenih']);
Route::post('benih/edit',[ApiController::class, 'editbenih']);
//alat
Route::get('alat/tampil', [ApiController::class, 'getalat']);
Route::post('alat/simpan', [ApiController::class, 'entryalat']);
Route::post('alat/hapus',[ApiController::class, 'hapusalat']);
Route::post('alat/edit',[ApiController::class, 'editalat']);

//helper

Route::post('kelurahan/tampil', [ApiController::class, 'kelurahan']);
Route::post('kecamatan/tampil', [ApiController::class, 'kecamatan']);
Route::post('kabkota/tampil', [ApiController::class, 'kabupaten']);
Route::get('provinsi/tampil', [ApiController::class, 'provinsi']);