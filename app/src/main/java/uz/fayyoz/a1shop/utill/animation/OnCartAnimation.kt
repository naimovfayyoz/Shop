package uz.fayyoz.a1shop.utill.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Path
import android.view.View



interface OnCartAnimation {

    public fun addAnim(view: View) {
        view.animate().apply {
            view.animate().scaleX(0.01f).scaleY(0.01f).duration = 1000
            view.animate().alpha(0f).duration = 2000
            val path = Path()
            path.arcTo(1f, 1f, 400f, 800f, 180f, -95f, false)
            val animation = ObjectAnimator.ofFloat(view, View.X, View.Y, path)
            animation.duration = 1500
            animation.start()
            setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator) {
                }

                override fun onAnimationEnd(p0: Animator) {
                    view.animate().scaleX(1f).scaleY(1f)
                    view.animate().translationX(0f).translationY(0f).alpha(1f)
                }

                override fun onAnimationCancel(p0: Animator) {

                }

                override fun onAnimationRepeat(p0: Animator) {

                }

            })
        }
    }
}