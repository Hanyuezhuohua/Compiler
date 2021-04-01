declare i32 @_str_length(i8* %0)
declare i8* @_str_substring(i8* %0, i32 %1, i32 %2)
declare i32 @_str_parseInt(i8* %0)
declare i32 @_str_ord(i8* %0, i32 %1)
declare i8* @_str_concat(i8* %0, i8* %1)
declare i1 @_str_eq(i8* %0, i8* %1)
declare i1 @_str_ne(i8* %0, i8* %1)
declare i1 @_str_lt(i8* %0, i8* %1)
declare i1 @_str_gt(i8* %0, i8* %1)
declare i1 @_str_le(i8* %0, i8* %1)
declare i1 @_str_ge(i8* %0, i8* %1)
declare void @_gbl_print(i8* %0)
declare void @_gbl_println(i8* %0)
declare void @_gbl_printInt(i32 %0)
declare void @_gbl_printlnInt(i32 %0)
declare i8* @_gbl_getString()
declare i32 @_gbl_getInt()
declare i8* @_gbl_toString(i32 %0)
declare i8* @malloc(i32 %0)
@INF = common global i32 zeroinitializer, align 4
@n = common global i32 zeroinitializer, align 4
@m = common global i32 zeroinitializer, align 4
@a = common global i32** zeroinitializer, align 4
@str0 = private unnamed_addr constant [2 x i8] c"-1", align 1
@str1 = private unnamed_addr constant [1 x i8] c" ", align 1
@str2 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	store i32 10000000, i32* @INF, align 4
	ret void 
}
define void @init()

0:
;prev: 
;next: 10 
	%1 = call i32 @_gbl_getInt()
	store i32 %1, i32* @n, align 4
	%2 = call i32 @_gbl_getInt()
	store i32 %2, i32* @m, align 4
	%3 = load i32, i32* @n, align 4
	%4 = mul i32 %3, 4
	%5 = add i32 %4, 4
	%6 = call i8* @malloc(i32 %5)
	%7 = bitcast i8* %6 to i32*
	store i32 %3, i32* %7, align 4
	%8 = getelementptr inbounds i32, i32* %7, i32 1
	%9 = bitcast i32* %8 to i32**
	br label %10
10:
;prev: 0 14 
;next: 14 23 
;iDom: 0
;DomFrontiers: 10 
	%11 = phi i32 [ 0, %0 ], [ %12, %14 ]
	%12 = add i32 %11, 1
	%13 = icmp ne i32 %11, %3
	br i1 %13, label %14, label %23
14:
;prev: 10 
;next: 10 
;iDom: 10
;DomFrontiers: 10 
	%15 = getelementptr inbounds i32, i32* %7, i32 %11
	%16 = bitcast i32* %15 to i32**
	%17 = load i32, i32* @n, align 4
	%18 = mul i32 %17, 4
	%19 = add i32 %18, 4
	%20 = call i8* @malloc(i32 %19)
	%21 = bitcast i8* %20 to i32*
	store i32 %17, i32* %21, align 4
	%22 = getelementptr inbounds i32, i32* %21, i32 1
	store i32* %22, i32** %16, align 4
	br label %10
23:
;prev: 10 
;next: 24 
;iDom: 10
	store i32** %9, i32*** @a, align 4
	br label %24
24:
;prev: 23 54 
;next: 29 30 
;iDom: 23
;DomFrontiers: 24 
	%25 = phi i32 [ , %23 ], [ %32, %54 ]
	%26 = phi i32 [ 0, %23 ], [ %55, %54 ]
	%27 = load i32, i32* @n, align 4
	%28 = icmp slt i32 %26, %27
	br i1 %28, label %29, label %30
29:
;prev: 24 
;next: 31 
;iDom: 24
;DomFrontiers: 24 
	br label %31
30:
;prev: 24 
;next: 35 
;iDom: 24
	br label %35
31:
;prev: 29 52 
;next: 39 45 
;iDom: 29
;DomFrontiers: 31 24 
	%32 = phi i32 [ 0, %29 ], [ %53, %52 ]
	%33 = load i32, i32* @n, align 4
	%34 = icmp slt i32 %32, %33
	br i1 %34, label %39, label %45
35:
;prev: 30 56 
;next: 46 51 
;iDom: 30
;DomFrontiers: 35 
	%36 = phi i32 [ 0, %30 ], [ %57, %56 ]
	%37 = load i32, i32* @n, align 4
	%38 = icmp slt i32 %36, %37
	br i1 %38, label %46, label %51
39:
;prev: 31 
;next: 52 
;iDom: 31
;DomFrontiers: 31 
	%40 = load i32**, i32*** @a, align 4
	%41 = getelementptr inbounds i32*, i32** %40, i32 %26
	%42 = load i32*, i32** %41, align 4
	%43 = getelementptr inbounds i32, i32* %42, i32 %32
	%44 = load i32, i32* @INF, align 4
	store i32 %44, i32* %43, align 4
	br label %52
45:
;prev: 31 
;next: 54 
;iDom: 31
;DomFrontiers: 24 
	br label %54
46:
;prev: 35 
;next: 56 
;iDom: 35
;DomFrontiers: 35 
	%47 = load i32**, i32*** @a, align 4
	%48 = getelementptr inbounds i32*, i32** %47, i32 %36
	%49 = load i32*, i32** %48, align 4
	%50 = getelementptr inbounds i32, i32* %49, i32 %36
	store i32 0, i32* %50, align 4
	br label %56
51:
;prev: 35 
;next: 58 
;iDom: 35
	br label %58
52:
;prev: 39 
;next: 31 
;iDom: 39
;DomFrontiers: 31 
	%53 = add i32 %32, 1
	br label %31
54:
;prev: 45 
;next: 24 
;iDom: 45
;DomFrontiers: 24 
	%55 = add i32 %26, 1
	br label %24
56:
;prev: 46 
;next: 35 
;iDom: 46
;DomFrontiers: 35 
	%57 = add i32 %36, 1
	br label %35
58:
;prev: 51 74 
;next: 65 73 
;iDom: 51
;DomFrontiers: 58 
	%59 = phi i32 [ , %51 ], [ %66, %74 ]
	%60 = phi i32 [ , %51 ], [ %67, %74 ]
	%61 = phi i32 [ , %51 ], [ %68, %74 ]
	%62 = phi i32 [ 0, %51 ], [ %75, %74 ]
	%63 = load i32, i32* @m, align 4
	%64 = icmp slt i32 %62, %63
	br i1 %64, label %65, label %73
65:
;prev: 58 
;next: 74 
;iDom: 58
;DomFrontiers: 58 
	%66 = call i32 @_gbl_getInt()
	%67 = call i32 @_gbl_getInt()
	%68 = call i32 @_gbl_getInt()
	%69 = load i32**, i32*** @a, align 4
	%70 = getelementptr inbounds i32*, i32** %69, i32 %66
	%71 = load i32*, i32** %70, align 4
	%72 = getelementptr inbounds i32, i32* %71, i32 %67
	store i32 %68, i32* %72, align 4
	br label %74
73:
;prev: 58 
;next: 
;iDom: 58
	ret void 
74:
;prev: 65 
;next: 58 
;iDom: 65
;DomFrontiers: 58 
	%75 = add i32 %62, 1
	br label %58
}
define i32 @main()

0:
;prev: 
;next: 1 
	call void @g_init()
	call void @init()
	br label %1
1:
;prev: 0 27 
;next: 7 8 
;iDom: 0
;DomFrontiers: 1 
	%2 = phi i32 [ , %0 ], [ %11, %27 ]
	%3 = phi i32 [ 0, %0 ], [ %28, %27 ]
	%4 = phi i32 [ , %0 ], [ %10, %27 ]
	%5 = load i32, i32* @n, align 4
	%6 = icmp slt i32 %3, %5
	br i1 %6, label %7, label %8
7:
;prev: 1 
;next: 9 
;iDom: 1
;DomFrontiers: 1 
	br label %9
8:
;prev: 1 
;next: 14 
;iDom: 1
	br label %14
9:
;prev: 7 79 
;next: 19 20 
;iDom: 7
;DomFrontiers: 1 9 
	%10 = phi i32 [ %4, %7 ], [ %24, %79 ]
	%11 = phi i32 [ 0, %7 ], [ %80, %79 ]
	%12 = load i32, i32* @n, align 4
	%13 = icmp slt i32 %11, %12
	br i1 %13, label %19, label %20
14:
;prev: 8 90 
;next: 21 22 
;iDom: 8
;DomFrontiers: 14 
	%15 = phi i32 [ %4, %8 ], [ %30, %90 ]
	%16 = phi i32 [ 0, %8 ], [ %91, %90 ]
	%17 = load i32, i32* @n, align 4
	%18 = icmp slt i32 %16, %17
	br i1 %18, label %21, label %22
19:
;prev: 9 
;next: 23 
;iDom: 9
;DomFrontiers: 9 
	br label %23
20:
;prev: 9 
;next: 27 
;iDom: 9
;DomFrontiers: 1 
	br label %27
21:
;prev: 14 
;next: 29 
;iDom: 14
;DomFrontiers: 14 
	br label %29
22:
;prev: 14 
;next: 
;iDom: 14
	ret i32 0
23:
;prev: 19 92 
;next: 33 51 
;iDom: 19
;DomFrontiers: 23 9 
	%24 = phi i32 [ 0, %19 ], [ %93, %92 ]
	%25 = load i32, i32* @n, align 4
	%26 = icmp slt i32 %24, %25
	br i1 %26, label %33, label %51
27:
;prev: 20 
;next: 1 
;iDom: 20
;DomFrontiers: 1 
	%28 = add i32 %3, 1
	br label %1
29:
;prev: 21 96 
;next: 52 60 
;iDom: 21
;DomFrontiers: 14 29 
	%30 = phi i32 [ 0, %21 ], [ %97, %96 ]
	%31 = load i32, i32* @n, align 4
	%32 = icmp slt i32 %30, %31
	br i1 %32, label %52, label %60
33:
;prev: 23 
;next: 62 78 
;iDom: 23
;DomFrontiers: 23 
	%34 = load i32**, i32*** @a, align 4
	%35 = getelementptr inbounds i32*, i32** %34, i32 %11
	%36 = load i32*, i32** %35, align 4
	%37 = getelementptr inbounds i32, i32* %36, i32 %24
	%38 = load i32**, i32*** @a, align 4
	%39 = getelementptr inbounds i32*, i32** %38, i32 %11
	%40 = load i32*, i32** %39, align 4
	%41 = getelementptr inbounds i32, i32* %40, i32 %3
	%42 = load i32**, i32*** @a, align 4
	%43 = getelementptr inbounds i32*, i32** %42, i32 %3
	%44 = load i32*, i32** %43, align 4
	%45 = getelementptr inbounds i32, i32* %44, i32 %24
	%46 = load i32, i32* %41, align 4
	%47 = load i32, i32* %45, align 4
	%48 = add i32 %46, %47
	%49 = load i32, i32* %37, align 4
	%50 = icmp sgt i32 %49, %48
	br i1 %50, label %62, label %78
51:
;prev: 23 
;next: 79 
;iDom: 23
;DomFrontiers: 9 
	br label %79
52:
;prev: 29 
;next: 81 83 
;iDom: 29
;DomFrontiers: 29 
	%53 = load i32**, i32*** @a, align 4
	%54 = getelementptr inbounds i32*, i32** %53, i32 %16
	%55 = load i32*, i32** %54, align 4
	%56 = getelementptr inbounds i32, i32* %55, i32 %30
	%57 = load i32, i32* %56, align 4
	%58 = load i32, i32* @INF, align 4
	%59 = icmp eq i32 %57, %58
	br i1 %59, label %81, label %83
60:
;prev: 29 
;next: 90 
;iDom: 29
;DomFrontiers: 14 
	%61 = getelementptr inbounds [0 x i8], [0 x i8]* @str2, i32 0, i32 0
	call void @_gbl_println(i8* %61)
	br label %90
62:
;prev: 33 
;next: 78 
;iDom: 33
;DomFrontiers: 78 
	%63 = load i32**, i32*** @a, align 4
	%64 = getelementptr inbounds i32*, i32** %63, i32 %11
	%65 = load i32*, i32** %64, align 4
	%66 = getelementptr inbounds i32, i32* %65, i32 %24
	%67 = load i32**, i32*** @a, align 4
	%68 = getelementptr inbounds i32*, i32** %67, i32 %11
	%69 = load i32*, i32** %68, align 4
	%70 = getelementptr inbounds i32, i32* %69, i32 %3
	%71 = load i32**, i32*** @a, align 4
	%72 = getelementptr inbounds i32*, i32** %71, i32 %3
	%73 = load i32*, i32** %72, align 4
	%74 = getelementptr inbounds i32, i32* %73, i32 %24
	%75 = load i32, i32* %70, align 4
	%76 = load i32, i32* %74, align 4
	%77 = add i32 %75, %76
	store i32 %77, i32* %66, align 4
	br label %78
78:
;prev: 33 62 
;next: 92 
;iDom: 33
;DomFrontiers: 23 
	br label %92
79:
;prev: 51 
;next: 9 
;iDom: 51
;DomFrontiers: 9 
	%80 = add i32 %11, 1
	br label %9
81:
;prev: 52 
;next: 94 
;iDom: 52
;DomFrontiers: 94 
	%82 = getelementptr inbounds [2 x i8], [2 x i8]* @str0, i32 0, i32 0
	call void @_gbl_print(i8* %82)
	br label %94
83:
;prev: 52 
;next: 94 
;iDom: 52
;DomFrontiers: 94 
	%84 = load i32**, i32*** @a, align 4
	%85 = getelementptr inbounds i32*, i32** %84, i32 %16
	%86 = load i32*, i32** %85, align 4
	%87 = getelementptr inbounds i32, i32* %86, i32 %30
	%88 = load i32, i32* %87, align 4
	%89 = call i8* @_gbl_toString(i32 %88)
	call void @_gbl_print(i8* %89)
	br label %94
90:
;prev: 60 
;next: 14 
;iDom: 60
;DomFrontiers: 14 
	%91 = add i32 %16, 1
	br label %14
92:
;prev: 78 
;next: 23 
;iDom: 78
;DomFrontiers: 23 
	%93 = add i32 %24, 1
	br label %23
94:
;prev: 81 83 
;next: 96 
;iDom: 52
;DomFrontiers: 29 
	%95 = getelementptr inbounds [1 x i8], [1 x i8]* @str1, i32 0, i32 0
	call void @_gbl_print(i8* %95)
	br label %96
96:
;prev: 94 
;next: 29 
;iDom: 94
;DomFrontiers: 29 
	%97 = add i32 %30, 1
	br label %29
}
